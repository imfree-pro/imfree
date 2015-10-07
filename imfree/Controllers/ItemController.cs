using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

using imfree.Lib;
using imfree.Models;

namespace imfree.Controllers
{
    public class ItemController : ApiController
    {
        IMFREEEntities entity = new IMFREEEntities();

        ReturnValue returnValue = new ReturnValue();

        public ReturnValue list([FromBody]RequestValue value, byte categorySN)
        {
            try
            {
                returnValue.error = 0;
                returnValue.message = "ok";

                List<ItemGetList_Result> items = entity.ItemGetList(categorySN).ToList<ItemGetList_Result>();
                returnValue.data = new
                {
                    items
                };
            }
            catch (Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value)); ;
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        public ReturnValue listAll([FromBody]RequestValue value)
        {
            try
            {
                returnValue.error = 0;
                returnValue.message = "ok";

                List<ItemGetList_Result> items = entity.ItemGetList(null).ToList<ItemGetList_Result>();
                returnValue.data = new
                {
                    items
                };
            }
            catch (Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value));
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }
    }
}
