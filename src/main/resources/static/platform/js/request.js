var jrequest = (function () {
    return {
        _request(url, method, contentType, params) {
            return new Promise((resolve, reject) => {
                $.ajax({
                    headers: {
                        authorization: ''
                    },
                    contentType: contentType,
                    dataType: "JSON",
                    data: params,
                    url: `${url}`,
                    type: method,
                    success: function (res, textStatus, request) {
                        // console.log(request.getResponseHeader("X-Total-Count"))
                        // 对请求回来的数据进行操作，修改数据格式
                        resolve({
                            data: res,
                            total: request.getResponseHeader("X-Total-Count")
                        })
                    }
                })
            })
        },
        get(url, params) {
            return new Promise((resolve, reject) => {
                this._request(url, "get", "application/json", params).then(res => {
                    if (res) {
                        resolve(res)
                    }
                })
            })
        },
        post(url, params, contentType) {
            return new Promise((resolve, reject) => {
                this._request(url,
                    "post",
                    contentType ? contentType : "application/x-www-form-urlencoded",
                    params
                ).then(res => {
                    if (res) {
                        resolve(res)
                    }
                })
            })
        },
        put(url, params) {
            return new Promise((resolve, reject) => {
                this._request(url, "put", "application/x-www-form-urlencoded", params).then(res => {
                    if (res) {
                        resolve(res)
                    }
                })
            })
        },
        delete(url, params) {
            return new Promise((resolve, reject) => {
                this._request(url, "delete", "application/json", params).then(res => {
                    if (res) {
                        resolve(res)
                    }
                })
            })
        }
    }
}())