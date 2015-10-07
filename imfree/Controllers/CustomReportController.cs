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
    public class CustomReportController : ApiController
    {
        IMFREEEntities entity = new IMFREEEntities();

        ReturnValue returnValue = new ReturnValue();

        public ReturnValue create([FromBody]dynamic value)
        {
            entity.CustomReportCreate(JsonConvert.SerializeObject(value));

            return null;
        }

        [HttpGet]
        public HttpResponseMessage list()
        {
            dynamic report = entity.CustomReportGetList().ToList<CustomReportGetList_Result>();

            System.Text.StringBuilder sb = new System.Text.StringBuilder();
            sb.Append("<html><head><style></style></head><body>");

            foreach (CustomReportGetList_Result r in report)
            {
                long sn = r.reportsn;
                string str = Convert.ToString(r.report);
                CustomReport customReport = JsonConvert.DeserializeObject<CustomReport>(str);

                sb.Append("<table border='1' style='border-collapse:collapse; border:1px gray solid; font-size:8pt;'>");
                sb.AppendFormat("<tr bgcolor=#FFFF00> <td> REPORT SN </td> <td>{0}</td> </tr>", sn);
                sb.AppendFormat("<tr> <td> LOGCAT </td> <td>{0}</td> </tr>", customReport.LOGCAT.Replace("\n", "<br />").Replace("\t", "<br />"));
                sb.AppendFormat("<tr> <td> APP_VERSION_NAME </td> <td>{0}</td> </tr>", customReport.APP_VERSION_NAME);
                sb.AppendFormat("<tr> <td> USER_APP_START_DATE </td> <td>{0}</td> </tr>", customReport.USER_APP_START_DATE);
                sb.AppendFormat("<tr> <td> CUSTOM_DATA </td> <td>{0}</td> </tr>", customReport.CUSTOM_DATA);
                sb.AppendFormat("<tr> <td> STACK_TRACE </td> <td>{0}</td> </tr>", customReport.STACK_TRACE.Replace("\n", "<br />").Replace("\t", "<br />"));
                sb.AppendFormat("<tr> <td> PHONE_MODEL </td> <td>{0}</td> </tr>", customReport.PHONE_MODEL);
                sb.AppendFormat("<tr> <td> BRAND </td> <td>{0}</td> </tr>", customReport.BRAND);
                sb.AppendFormat("<tr> <td> ANDROID_VERSION </td> <td>{0}</td> </tr>", customReport.ANDROID_VERSION);
                sb.AppendFormat("<tr> <td> APP_VERSION_CODE </td> <td>{0}</td> </tr>", customReport.APP_VERSION_CODE);
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

    public class CustomReport
    {
        public string LOGCAT { get; set; }
        public string APP_VERSION_NAME { get; set; }
        public string USER_APP_START_DATE { get; set; }
        public dynamic CUSTOM_DATA { get; set; }
        public string STACK_TRACE { get; set; }
        public string PHONE_MODEL { get; set; }
        public string BRAND { get; set; }
        public string ANDROID_VERSION { get; set; }
        public string APP_VERSION_CODE { get; set; }
    }
}
