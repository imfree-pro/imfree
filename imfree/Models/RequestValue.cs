using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace imfree.Models
{
    public class RequestValue
    {
        /// <summary>
        /// 구글 토큰
        /// </summary>
        public string token = string.Empty;

        /// <summary>
        /// 정합성 데이터
        /// </summary>
        public string guid = string.Empty;

        /// <summary>
        /// 요청 데이터
        /// </summary>
        public dynamic data;
    }
}