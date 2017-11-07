var oFocus = $('#focus'),
			oRight = oFocus.find('.right'),
			aRLi = oRight.find('li'),
			index = 0;
		aRLi.click(function() {
			index = $(this).index()
			$(this).addClass('active').siblings().removeClass();
		})
		$(".right ul li").eq(0).click(function() {
			$("#parentIframe").attr("src", "teach_DifferentGrades.html")
		})
		$(".right ul li").eq(1).click(function() {
			$("#parentIframe").attr("src", "teach_ResultsComparison.html")
		})