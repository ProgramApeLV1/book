var jrequest = (function () {
    return {
        _request(url, method, contentType, params, async) {
            return new Promise((resolve, reject) => {
                $.ajax({
                    headers: {
                        authorization: ''
                    },
                    async: async,
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
                    },
                    error: function (xhr, status, error) {
                        console.error(xhr)
                        console.error(status)
                        console.error(error)
                        reject(xhr.responseJSON);
                    }
                })
            })
        },
        get(url, params, notAsync) {
            return new Promise((resolve, reject) => {
                this._request(url, "get", "application/json",
                    params,
                    !notAsync
                ).then(res => {
                    if (res) {
                        resolve(res)
                    }
                }).catch(e => {
                    reject(e);
                })
            })
        },
        post(url, params, contentType, notAsync) {
            return new Promise((resolve, reject) => {
                this._request(url,
                    "post",
                    contentType ? contentType : "application/x-www-form-urlencoded",
                    params,
                    !notAsync
                ).then(res => {
                    if (res) {
                        resolve(res)
                    }
                }).catch(e => {
                    reject(e);
                })
            })
        },
        put(url, params, contentType, notAsync) {
            return new Promise((resolve, reject) => {
                this._request(url,
                    "put",
                    contentType ? contentType : "application/x-www-form-urlencoded",
                    params,
                    !notAsync
                ).then(res => {
                    if (res) {
                        resolve(res)
                    }
                }).catch(e => {
                    reject(e);
                })
            })
        },
        delete(url, params, contentType, notAsync) {
            return new Promise((resolve, reject) => {
                this._request(url,
                    "delete",
                    contentType ? contentType : "application/json",
                    params,
                    !notAsync
                ).then(res => {
                    if (res) {
                        resolve(res)
                    }
                }).catch(e => {
                    reject(e);
                })
            })
        }
    }
}())