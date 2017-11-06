var oFocus = $('#focus'),
	oRight = oFocus.find('.right'),
	aRLi = oRight.find('li'),
	index = 0;
aRLi.click(function() {
	index = $(this).index()
	$(this).addClass('active').siblings().removeClass();
});
$(".right ul li").eq(0).click(function() {
	$("#parentIframe").attr("src", "zznueg/portal/leaderplatform/dataStatAvgScoreComp");
});
$(".right ul li").eq(1).click(function() {
	$("#parentIframe").attr("src", "zznueg/portal/leaderplatform/dataStatFourCntAvgScoreComp");
});
$(".right ul li").eq(2).click(function() {
	$("#parentIframe").attr("src", "zznueg/portal/leaderplatform/dataStatCompreScoreTop10");
});
$(".right ul li").eq(3).click(function() {
	$("#parentIframe").attr("src", "zznueg/portal/leaderplatform/dataStatFailStuCountCompare");
});
$(".right ul li").eq(4).click(function() {
	$("#parentIframe").attr("src", "zznueg/portal/leaderplatform/dataStatAcaPassRateMain");
});
$(".right ul li").eq(5).click(function() {
	$("#parentIframe").attr("src", "zznueg/portal/leaderplatform/dataStatCompreAvgScoreComp");
});
$(".right ul li").eq(6).click(function() {
	$("#parentIframe").attr("src", "zznueg/portal/leaderplatform/dataStatAcaExamCountComp");
});