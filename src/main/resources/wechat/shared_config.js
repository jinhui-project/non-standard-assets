const wxConfig = {
    appId: 'wxc8676e52af4c87ed',
    timestamp: $timestamp,
    nonceStr: '$nonceStr',
    signature: '$signature',
    title: '$title',
    link: '$link'
};
wx.config({
    debug: false,
    appId: wxConfig.appId,
    timestamp: parseInt(wxConfig.timestamp),
    nonceStr: wxConfig.nonceStr,
    signature: wxConfig.signature,
    jsApiList: [
        'onMenuShareTimeline',
        'onMenuShareAppMessage'
    ]
});
wx.ready(function(){
    wx.onMenuShareTimeline({
        title: wxConfig.title,
        link: wxConfig.link,
        imgUrl: 'http://m.jinfeibiao.com/src/images/logo1.png',
        success: function () {
        },
        complete: function () {
        }
    });
    wx.onMenuShareAppMessage({
        title: wxConfig.title,
        desc: '金飞镖资产交易平台',
        link: wxConfig.link,
        imgUrl: 'http://m.jinfeibiao.com/src/images/logo1.png',
        type: 'link',
        dataUrl: '',
        success: function () {
        },
        complete: function () {
        }
    });
});