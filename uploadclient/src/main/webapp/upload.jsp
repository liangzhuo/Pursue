<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传</title>
<link rel="stylesheet" type="text/css" href="css/uploadify.css" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.js"></script>
<script type="text/javascript">
	$(function() {
	    $('#file_upload').uploadify({
            'auto'			: false,//是否自动上传
	    	'height'        : 80,   
            'width'         : 150,    
            'buttonText'    : '选择课件', 
            'fileObjName'   : 'file',
        	'swf'      		: 'css/uploadify.swf',
	        //'uploader' 		: 'http://192.168.0.182:8090/uploadserver/upload?userid=admin&ftpserverid=2&cwtype=1',
	        'uploader' 		: 'http://192.168.0.208:9080/uploadfile?userid=admin&ftpserverid=2&cwtype=1',
        	'auto'          : false,  
        	'fileTypeDesc'  : '支持的格式：',
            'fileTypeExts'  : '*.mp4;*.MP4',
            'removeTimeout' : 1,
            //'formData'      : {'userid':'admin','ftpserverid':'2','cwtype':1},  
            'onUploadStart' : function(file) {  
                //$("#file_upload").uploadify("settings", "formData", {'userid':'admin','ftpserverid':'2','cwtype':1});  
                //$("#file_upload").uploadify("settings", "qq", );  
            },
            'onUploadComplete' : function(file) {
            	alert(file.name);
            },
            'onQueueComplete'  : function(queueData) {
            	console.log(queueData);
            	alert("上传成功!");
            },
          	//检测FLASH失败调用
            'onFallback': function () {
                alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
            },
          	//返回一个错误，选择文件的时候触发
            'onSelectError': function (file, errorCode, errorMsg) {
                switch (errorCode) {
                    case -100:
                        alert("上传的文件数量已经超出系统限制的" + $('#file_upload').uploadify('settings', 'queueSizeLimit') + "个文件！");
                        break;
                    case -110:
                        alert("文件 [" + file.name + "] 大小超出系统限制的" + $('#file_upload').uploadify('settings', 'fileSizeLimit') + "大小！");
                        break;
                    case -120:
                        alert("文件 [" + file.name + "] 大小异常！");
                        break;
                    case -130:
                        alert("文件 [" + file.name + "] 类型不正确！");
                        break;
                }
            },
          	//上传到服务器，服务器返回相应信息到data里
            'onUploadSuccess': function (fileObj, data, response) {
            	console.log(data);
                alert(data);
                //alert(fileObj.name);
                all_name += data;
                //fileObj.name = "";
                alert(all_name); //怎么取得这个值？？然后把他放到txt1那里去
                $("#txt1").val(all_name);
            }
            
	    });
	});
</script>
</head>
<body>
	<input type="file" name="file_upload" id="file_upload" value="上传"/>
	<hr>  
        <a href="javascript:$('#file_upload').uploadify('upload','*')">开始上传</a>      
        <a href="javascript:$('#file_upload').uploadify('cancel', '*')">取消所有上传</a> 
</body>
</html>