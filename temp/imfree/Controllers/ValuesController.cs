using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;

namespace imfree.Controllers
{
    public class ValuesController : ApiController
    {
        // GET api/values
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET api/values/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/values
        public void Post([FromBody]string value)
        {
            string str = value;
        }

        // PUT api/values/5
        public void Put(int id, [FromBody]string value)
        {
            int sn = id;
            string str = value;
        }

        // DELETE api/values/5
        public void Delete(int id)
        {
            int sn = id;
        }
    }
}