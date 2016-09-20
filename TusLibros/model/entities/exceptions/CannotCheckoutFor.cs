﻿using System;
using System.Runtime.Serialization;

namespace TusLibros.model.entities.exceptions
{
    [Serializable]
    internal class CannotCheckoutFor : Exception // TODO: voletear
    {
        public CannotCheckoutFor()
        {
        }

        public CannotCheckoutFor(string message) : base(message)
        {
        }

        public CannotCheckoutFor(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected CannotCheckoutFor(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}