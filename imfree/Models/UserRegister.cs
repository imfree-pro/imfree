using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace imfree.Models
{
    public class UserRegister
    {
        public DateTime clientsyncdate { get; set; }
        public DateTime serversyncdate { get; set; }
        public Device user { get; set; }
        public List<Phone> contacts { get; set; }
    }
}