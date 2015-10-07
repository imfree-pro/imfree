using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace imfree.Models
{
    public class SuggestRegister
    {
        public byte categorysn { get; set; }
        public byte itemsn { get; set; }
        public List<string> privateuser { get; set; }
    }
}