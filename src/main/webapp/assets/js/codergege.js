/**
 *	Directory
 * ***************** BASIC ***************************
 *	ajax(opts):
 *		封装 ajax
 *		opts: json 格式的参数
 *		详细说明:
 *		method: 请求方式: GET/POST, 默认值: 'GET';
 *		url: 请求 url, 默认当前页地址;
 *		data: string, json; 推荐用 json 格式, 比较方便;
 *		async: 是否异步 true/false, 默认 true;
 *		cache: 是否缓存 true/false, 默认 true;
 *		contentType: HTTP头信息, 默认值: 'application/x-www-form-urlencoded';
 *		success: 请求成功后的回调函数; 接收一个 data 参数, 值是 
 *			JSON.parse(oXhr.responseText), 函数内 this 指向 oXhr;
 *			
 *		error: 请求失败后的回调函数; 无参数, this 指向 oXhr;
 *
 *  compareArrays(a, b):
 *		比较数组. 值比较, 而不是 == 这种引用比较.
 * 
 *	arrIndexOf(arr, v): 
 *		数组是否包含 v, 如果是返回下标, 不是返回 -1.
 *
 *	addClass(obj, className):
 *		给 obj 添加 class.
 *
 *	removeClass(obj, className):
 *		移除 obj 的 class.
 *
 * ***************** PROJECT ***************************
 *	import('b.js'): js 引用 js
 *
 *	getRootPath(): 获取项目路径
 *
 *	getProjectName(): 获取项目名称
 *
 * ***************** STYLE ***************************
 *	getStyle(obj,attr): 获取元素的 attr 样式
 *
 *	getPos(obj): 获取距离页面位置 
 *
 *	-- getParentWidth(obj): 获取元素父级容器的宽
 *	-- getParentHeight(obj): 获取元素父级容器的高
 *		如果父级没有宽高怎么解决, 有没有现成的库, 父级是 body 怎么获取屏幕宽高
 * 
 * ***************** MOVEMENT ************************
 *	function doMove(obj, attr, step, boundary, speed, callback): 运动函数
 *		@param {Object} obj 目标元素
 *		@param {Object} attr 样式名称, 比如位置的 left, top; 宽高的 width, height
 *		@param {Object} step 每次变化的步长
 *		@param {Object} boundary 边界值
 *		@param {Object} speed 单位毫秒
 *		@param {Object} callback 回调函数	
 * 
 * 
 * ***************** MOVEMENT ************************
 * 
 *	tipInput(obj): 输入框获取焦点时清空提示, 失去焦点时如果输入框为空, 则显示提示.
 * 
 */

/**
 * ***************** BASIC ***************************
 */

function ajax(opts) {
	//一.设置默认参数
	var defaults = {    
		method: 'GET',
		url: '',
		data: '',        
		async: true,
		cache: true,
		contentType: 'application/x-www-form-urlencoded',
		success: function() {},
		error: function() {}
	};    

	//二.用户参数覆盖默认参数    
	for(var key in opts){
		defaults[key] = opts[key];
	}

	//三.对数据进行处理
	if(typeof defaults.data === 'object'){    //处理 data
		var str = '';
		for(var dataKey in defaults.data){
			str += dataKey + '=' + defaults.data[dataKey] + '&';
		}
		defaults.data = str.substring(0, str.length - 1);
	}

	defaults.method = defaults.method.toUpperCase();    //处理 method

	defaults.cache = defaults.cache ? '' : '&' + new Date().getTime() ;//处理 cache

	if(defaults.method === 'GET' && (defaults.data || defaults.cache)) {
		defaults.url += '?' + defaults.data + defaults.cache;    //处理 url    
	}    

	//四.开始编写ajax
	//1.创建ajax对象
	var oXhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
	//2.和服务器建立联系，告诉服务器你要取什么文件
	oXhr.open(defaults.method, defaults.url, defaults.async);
	//3.发送请求
	if(defaults.method === 'GET') {
		oXhr.send(null);
	} else {
		oXhr.setRequestHeader("Content-type", defaults.contentType);
		oXhr.send(defaults.data);
	}    
	//4.等待服务器回应
	oXhr.onreadystatechange = function() {
		if(oXhr.readyState === 4){
			if(oXhr.status === 200)
				// 让 this 指向 oXhr
				defaults.success.call(oXhr, JSON.parse(oXhr.responseText));
			else{
				defaults.error.call(oXhr);
			}
		}
	};
}

function compareArrays(a, b) {
	if(a.length != b.length) return false;
	for(var i = 0; i < a.length; i++) {
		if(a[i] !== b[i]) return false;
	}

	return true;
}

function arrIndexOf(arr, v) {

	for (var i=0; i<arr.length; i++) {
		if (arr[i] == v) {
			return i;
		}
	}
	return -1;

}

function addClass(obj, className) {

	if (obj.className === '') {
		obj.className = className;
	} else {
		var arrClassName = obj.className.split(' ');
		if ( arrIndexOf(arrClassName, className) == -1 ) {
			obj.className += ' ' + className;
			/*arrClassName.push(className);
			  obj.className = arrClassName.join(' ');*/
		}
	}

}

function removeClass(obj, className) {

	if (obj.className !== '') {
		var arrClassName = obj.className.split(' ');
		var _index = arrIndexOf(arrClassName, className);
		if ( _index != -1 ) {
			arrClassName.splice(_index, 1);
			obj.className = arrClassName.join(' ');
		}
	}

}
/**
 * ***************** PROJECT ***************************
 */

/**
 *  js import js.
 *  import('b.js') js 引用 js
 */
function include(path){ 
	var a=document.createElement("script");
	a.type = "text/javascript"; 
	a.src=path; 
	var head=document.getElementsByTagName("head")[0];
	head.appendChild(a);
}

/**
 * 获取项目地址
 */
function getRootPath() {
	//获取当前网址，如： http://localhost:8088/jquery/easyui/login.jsp
	var curWwwPath = window.document.location.href;
	//获取主机地址之后的目录，如： jquery/easyui/login.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:8088
	var localhostPath = curWwwPath.substring(0, pos);
	//获取带"/"的项目名，如：/jquery
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return(localhostPath + projectName);
}

function getProjectName() {
	var pathName = window.document.location.pathname;
	var projectName = pathName.substring(1, pathName.substr(1).indexOf('/') + 1);
	return projectName;
}


/**
 * ***************** STYLE ***************************
 */

/**
 * 获取距离页面位置 getPos(obj)
 */
function getPos(obj) {
	var pos = { top:0, left:0 };

	while(obj) {
		pos.top += obj.offsetTop;
		pos.left += obj.offsetLeft;

		obj = obj.offsetParent;
	}

	return pos;
}


/**
 * getStyle(obj, attr)
 * 获取 obj 的 attr 样式
 */
function getStyle(obj, attr) {
	return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj)[attr];
}


/**
 * ***************** MOVEMENT ***************************
 */

/**
 * 
 * @param {Object} obj 目标元素
 * @param {Object} attr 样式名称, 比如位置的 left, top; 宽高的 width, height
 * @param {Object} step 每次变化的步长
 * @param {Object} boundary 
 * @param {Object} speed 单位毫秒
 * @param {Object} callback 回调函数
 */
function doMove(obj, attr, step, boundary, speed, callback) {
	var cur = parseInt(getStyle(obj, attr));
	step = cur < boundary ? step : -step;
	clearInterval(obj.timer);
	obj.timer = setInterval(function() {
		cur += step;
		if(step > 0 && cur > boundary || step < 0 && cur < boundary) {
			cur = boundary;
		}
		obj.style[attr] = cur + 'px';
		if(cur == boundary) {
			clearInterval(obj.timer);
			callback();
		}
	}, speed);
}

/**
 * ***************** FORM ***************************
 */

function tipInput(obj) {

	var tip = obj.value;

	obj.onfocus = function() {
		if(this.value == tip) {
			this.value = '';
		}
	};

	obj.onblur = function() {
		if(this.value === '') {
			this.value = tip;
		}
	};

}
