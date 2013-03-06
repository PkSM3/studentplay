/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function validateUrl(questionnumber) {
	console.log("En validateUrl");
	console.log("urlInput"+questionnumber);
	var url = document.getElementById("urlInput"+questionnumber).value;
	if(url.search("http://")!=-1){
		url=url.slice(7,url.length);
	}
	console.log(url);
	var regexp = /^[a-z0-9-\.]+\.[a-z]{2,4}\/?([^\s<>\#%"\,\{\}\\|\\\^\[\]`]+)?$/;
	var result = regexp.test(url);
	if(url.match("wikipedia")!=null) result=true;
	if(result) {
		$('#Loading').html("<img src=\"/assets/images/Loader.gif\"></img>");
		$("#ErrorMessage").empty();
		$.ajax({
			type: 'GET',
			//url: 'http://tina.iscpif.fr/getJsonFromUrl/textCrawler.php',
			url: 'http://localhost/getJsonFromUrl/textCrawler.php',
			data: "url="+encodeURIComponent(url),
			contentType: "application/json",
			dataType: 'jsonp',
			success : function(data){ 
				if(data.url == null) data.url = "";
				if(data.title == null) data.title = data.titleEsc;
				if(data.description == null) data.description = data.descriptionEsc;
				if(data.title == null) data.title = "";
				if(data.description == null) data.description = "";
				if(data.images == null || data.images==false) data.images = "";
				else {
					console.log(data.images);
					images = data.images.split("|");
					console.log(images.length);
					if(images.length>1){
						document.getElementsByName("image")[0].value=images[0];
						$('#theImage').html("<img src=\""+images[0]+"\""+"</img>");
					}
					else {
						document.getElementsByName("image")[0].value=data.images;
						$('#theImage').html("<img width=150 src=\""+data.images+"\""+"</img>");
					}
				document.getElementsByName("url")[0].value=data.url;
				document.getElementsByName("name")[0].value=data.title;
				document.getElementsByName("descriptionUrl")[0].value=data.description;
				$('#Loading').html("");
				$("#formBlock").show(); 
                                }
                        },
			error: function(){ 
				initAddUrl();
				$("#ErrorMessage").empty().text("Page Not found.");
			}
	    	});
	}
	else {
		$("#ErrorMessage").empty().text("Not a valid url");
		$("#formBlock").hide();
	}

}