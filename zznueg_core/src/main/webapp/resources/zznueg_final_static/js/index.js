var oFocus = $('#focus'),
			oRight = oFocus.find('.right'),
			aRLi = oRight.find('li'),
			index = 0;
		aRLi.click(function() {
			index = $(this).index()
			$(this).addClass('active').siblings().removeClass();
		})
		$(".right ul li").eq(0).click(function() {
			$("#parentIframe").attr("src", "index_average_score.html")
		})
		$(".right ul li").eq(1).click(function() {
			$("#parentIframe").attr("src", "index_fours.html")
		})
		$(".right ul li").eq(2).click(function() {
			$("#parentIframe").attr("src", "index_mostHeight.html")
		})
		$(".right ul li").eq(3).click(function() {
			$("#parentIframe").attr("src", "index_failed.html")
		})
		$(".right ul li").eq(4).click(function() {
			$("#parentIframe").attr("src", "index_all.html")
		})
		$(".right ul li").eq(5).click(function() {
			$("#parentIframe").attr("src", "index_integrated.html")
		})
		$(".right ul li").eq(6).click(function() {
			$("#parentIframe").attr("src", "index_number.html")
		})