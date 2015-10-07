using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

using imfree.Models;

namespace imfree.Controllers
{
    public class ErrorLogController : ApiController
    {
        IMFREEEntities entity = new IMFREEEntities();

        [HttpGet]
        public HttpResponseMessage list()
        {
            dynamic errorlog = entity.ErrorLogGetList().ToList<ErrorLogGetList_Result>();

            System.Text.StringBuilder sb = new System.Text.StringBuilder();
            sb.Append("<html><head><style></style></head><body>");

            foreach (ErrorLogGetList_Result e in errorlog)
            {
                sb.Append("<table border='1' style='border-collapse:collapse; border:1px gray solid; font-size:8pt;'>");
                sb.AppendFormat("<tr bgcolor=#FFFF00> <td> ERROR SN </td> <td>{0}</td> </tr>", e.logsn);
                sb.AppendFormat("<tr> <td> IP </td> <td>{0}</td> </tr>", e.ip);
                sb.AppendFormat("<tr> <td> URI </td> <td>{0}</td> </tr>", e.uri);
                sb.AppendFormat("<tr> <td> SOURCE </td> <td>{0}</td> </tr>", e.source);
                sb.AppendFormat("<tr> <td> METHOD </td> <td>{0}</td> </tr>", e.method);
                sb.AppendFormat("<tr> <td> DATE </td> <td>{0}</td> </tr>", e.dtcreate);
                sb.AppendFormat("<tr> <td> ERROR </td> <td>{0}</td> </tr>", e.error);
                sb.AppendFormat("<tr> <td> TRACE </td> <td>{0}</td> </tr>", e.trace.Replace("\n", "<br />").Replace("\t", "<br />"));
                sb.AppendFormat("<tr> <td> INPU JSON </td> <td>{0}</td> </tr>", e.json);
                sb.Append("<table>");

                sb.Append("<hr />");
            }
            sb.Append("</body></html>");

            var response = new HttpResponseMessage();
            response.Content = new StringContent(sb.ToString());
            response.Content.Headers.ContentType = new System.Net.Http.Headers.MediaTypeHeaderValue("text/html");

            return response;
        }
    }
}
