/*
*
*  name: detailMotheds
*  author: markbro
*  date: 2019/04/17 14:15
*
*/
$(function(){
    brandInit();
});
var brandInit = function(){
    var brandArray = [
        {img:"https://www.duoguyu.com/dist/images/brand/brand-pt-cms.jpg",url:"http://www.pt-cms.com?from=duoguyu.com" },
        {img:"https://www.duoguyu.com/dist/images/brand/brand-aliyun.jpg",url:"https://promotion.aliyun.com/ntms/yunparter/invite.html?userCode=psmzr0yi" },
        ];
    var _brand = parseInt(Math.random()*(brandArray.length));
    var currentBrand = brandArray[_brand];
    var brandHtml = '<a href="'+ currentBrand.url +'" target="_blank"><img class="img" src="'+ currentBrand.img +'"></a>';
    $("#brandGrid").html(brandHtml);
}
var toreply = function(obj){
    if($("#rep_" + obj).css("display") == "none"){
        $("#rep_" + obj).css("display", "block");
    }else{
        $("#rep_" + obj).css("display", "none");
    }
};
var check_comm = function(obj){
    var content = obj.content.value;
	if(content === ''){
	    layer.msg('哎呀，你好像忘记写评论了？');
		return false;
	}
	return true;
};
var check_rep = function(obj){
    var content = obj.content.value;
	if(content === ''){
	    layer.msg('哎呀，你好像忘记写评论了？');
		return false;
	}
	return true;
};

