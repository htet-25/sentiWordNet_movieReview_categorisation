jQuery(document).ready(function() {
	"use strict";

	/* Tab Panel */
	jQuery(document).ready(function() {
		jQuery(".tabs-menu a").click(function(event) {
			event.preventDefault();
			jQuery(this).parent().addClass("current");
			jQuery(this).parent().siblings().removeClass("current");
			var tab = $(this).attr("href");
			jQuery(".tab-content").not(tab).css("display", "none");
			jQuery(tab).fadeIn();
		});
	});
	
	
	/* Search Accordion Panel */
	var acc = document.getElementsByClassName("accordion");
	var i;

	for (i = 0; i < acc.length; i++) {
	    acc[i].onclick = function(){
	        this.classList.toggle("active");
	        this.nextElementSibling.classList.toggle("show");
	  }
	}
	
	/* Scroll Top */
	jQuery(document).ready(function() {
		
		window.onscroll = function() {
		    if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
		    	$('#scrollTop').css({ display: "block" });
		    } else {
		    	$('#scrollTop').css({ display: "none" });
		    }
		}
		
		$('#scrollTop').click(function() {
	        $('html, body').animate({
	            scrollTop: 0
	        }, 800);
		});	
	});
	
	
	
});