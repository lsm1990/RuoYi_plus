/*
*
*  name: detailMotheds
*  author: markbro
*  date: 2019/04/17 14:15
*
*/
$(function(){
    
    //加载页面时执行一次
    resourceChange();
    //监听浏览器宽度的改变
    window.onresize = function(){
        //console.log('监听浏览器宽度的改变');
        resourceChange();
    };
    
});

// 移动端菜单 - open
var touchMenuOpen = function(){
    $('#touchMenuOpen').on('click',function(){
        $('#menuLayout').addClass('on');
    })
}
// 移动端菜单 - close
var touchMenuClose = function(){
    $('#touchMenuClose').on('click',function(){
        $('#menuLayout').removeClass('on');
    })
}

var indexBannerInit = function(){
    // indexFocusSwiper
    var indexFocusSwiper = new Swiper ('#indexTopFocus', {
        autoplay: { disableOnInteraction:false, delay:5000,},
        pagination: { el: '#indexTopFocus .swiper-pagination', clickable: true,},
        navigation: { nextEl: '#indexTopFocus .swiper-button-next', prevEl: '#indexTopFocus .swiper-button-prev',},
        effect: 'fade',
        loop: true,
    });
}

var resourcePcInit = function(){
    indexBannerInit();
    // resourceGrid
    var resourceSwiper = new Swiper ('#resourceGrid', {
        autoplay: { disableOnInteraction:false, delay:3600,},
        navigation: { nextEl: '#resourceGrid .swiper-button-next', prevEl: '#resourceGrid .swiper-button-prev',},
        slidesPerView: 3,
        //spaceBetween: 20,
        //loop: true,
    });
}
var resourceWapInit = function(){
    indexBannerInit();
    // resourceGrid
    var resourceSwiper = new Swiper ('#resourceGrid', {
        autoplay: { disableOnInteraction:false, delay:3600,},
        navigation: { nextEl: '#resourceGrid .swiper-button-next', prevEl: '#resourceGrid .swiper-button-prev',},
        slidesPerView: 2,
        //spaceBetween: 20,
        //loop: true,
    });
}

// 监听浏览器 - 适配 资源 swiper
var resourceChange = function(){
    var docWidth = document.body.clientWidth;
    var indexSwiperStatus = $('#indexTopFocus').length>0;
    //console.log('indexSwiperStatus:',indexSwiperStatus);
    if(docWidth <= 768){
        // 移动端
        if(indexSwiperStatus){
            resourceWapInit();
        }
        touchMenuOpen();
        touchMenuClose();
    }else{
        // pc端
        if(indexSwiperStatus){
            resourcePcInit();
        }
    }
    
}
