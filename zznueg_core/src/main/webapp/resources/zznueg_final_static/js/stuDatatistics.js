var oFocus = $('#focus'),
			oRight = oFocus.find('.right'),
			aRLi = oRight.find('li'),
			index = 0;
		aRLi.click(function() {
			index = $(this).index()
			$(this).addClass('active').siblings().removeClass();
		})
		$(".right ul li").eq(0).click(function() {
			$("#parentIframe").attr("src", "stuAll.html")
		})
		$(".right ul li").eq(1).click(function() {
			$("#parentIframe").attr("src", "stuJilu.html")
		})
		$(".right ul li").eq(2).click(function() {
			$("#parentIframe").attr("src", "stuDuibi.html")
		})
		$(".right ul li").eq(3).click(function() {
			$("#parentIframe").attr("src", "stuLeida.html")
		})
		$(".right ul li").eq(4).click(function() {
			$("#parentIframe").attr("src", "stuTop10.html")
		})