(function( factory ) {
    if ( !window.jQuery ) {
        alert('jQuery is required.')
    }

    jQuery(function() {
        factory.call( null, jQuery );
    });
})(function( $ ) {
// -----------------------------------------------------
// ------------ START ----------------------------------
// -----------------------------------------------------

// ---------------------------------
// ---------  Uploader -------------
// ---------------------------------
var Uploader = (function() {
    // -------setting-------
    // 如果使用原始大小，超大的图片可能会出现 Croper UI 卡顿，所以这里建议先缩小后再crop.
    var FRAME_WIDTH = 1600;

    var _ = WebUploader;
    var Uploader = _.Uploader;
    var uploaderContainer = $('.uploader-container');
    var uploader, file;
    if ( !Uploader.support() ) {
        alert( 'Web Uploader 不支持您的浏览器！');
        throw new Error( 'WebUploader does not support the browser you are using.' );
    }
    return {
        init: function( selectCb ) {
            uploader = new Uploader({
                pick: {
                    id: '#filePicker',
                    multiple: uploadUtil.multiple
                },
                accept:uploadUtil.accept,
                runtimeOrder: 'html5,flash',
                // 设置用什么方式去生成缩略图。
                // 禁掉分块传输，默认是开起的。
                chunked: false,
                // 禁掉上传前压缩功能，因为会手动裁剪。
                compress: false,
                // fileSingleSizeLimit: 2 * 1024 * 1024,
                //文件上传地址：
                server: uploadUtil.server,
                swf: '../Uploader.swf',
                //fileNumLimit: 1,
                duplicate:true,
                onError: function() {
                    var args = [].slice.call(arguments, 0);
                    console.log(args);
                    alert(args.join('\n')+"上传失败");
                }
            });
            uploader.on("uploadBeforeSend",function (object,data,headers){
                data['folder']=uploadUtil.parentDirectory;
            });
            uploader.on('fileQueued', function( _file ) {
                file = _file;
                this.upload();//加入队列后立即触发上传事件
            });

            // 上传成功事件
            uploader.on('uploadSuccess',function (file,response) {
                // 返回的json对象
              if(response.result==200){
                  uploadUtil.afterUpload(response);
              }
            });
            uploader.on('startUpload',function () {
                // 返回的json对象
                uploadUtil.beforUpload();
            });

        },
        upload: function() {
            uploader.upload();
        }
    }
})();

// ------------------------------
// -----------logic--------------
// ------------------------------
var container = $('.uploader-container');

Uploader.init(function(src) {
    // 隐藏选择按钮。
    // container.addClass('webuploader-element-invisible');
    Uploader.upload();
    // 当用户选择上传的时候，开始上传。
});



// -----------------------------------------------------
// ------------ END ------------------------------------
// -----------------------------------------------------
});
