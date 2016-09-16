﻿using System;
using NHibernate;
using NHibernate.Criterion;
using TusLibros.clocks;
using TusLibros.lib;
using TusLibros.repositories;

namespace TusLibros.app
{
    internal class PersistentYourBooksApplication : IYourBooksApplication
    {
        public IClock Clock { get; set; }

        public PersistentYourBooksApplication()
        {
            Clock = new IntegrationClock();
        }

        public Cart CreateCart()
        {
            Cart aCart = new Cart();

            ISession session = SessionManager.OpenSession();
            ITransaction transaction = session.BeginTransaction();

            session.Save(new UsersSession(aCart, Clock.TimeNow()), session);

            transaction.Commit();

            return aCart;
        }

        public void AddAQuantityOfAnItem(int quantity, string aBook, Guid aCartId)
        {
            ISession session = SessionManager.OpenSession();
            ITransaction transaction = session.BeginTransaction();

            UsersSession userSession = session
                .CreateCriteria(typeof(UsersSession))
                .Add(Restrictions.Eq("CartId", aCartId))
                .UniqueResult<UsersSession>();

            userSession.VerifyCartExpired(Clock.TimeNow());
            Cart aCart = userSession.Cart;
            aCart.AddItemSomeTimes(aBook, quantity);
            session.Update(userSession, session);

            transaction.Commit();
        }
    }
}