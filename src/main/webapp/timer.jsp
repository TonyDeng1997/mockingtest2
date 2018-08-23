<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>



<style>
p {
  text-align: right;
  font-size: 60px;
  margin-top:0px;
}

textarea {
  resize: none;
}

.center {
margin: auto;
width: 50%;
/*padding: 10px;*/.
}
</style>

<script>
  // Set the date we're counting down to

  var testTime= 1;
  var testends=0;
  if(testends == 0){
    testends=new Date().getTime()+testTime*60000;
  }
  
  // Update the count down every 1 second
  var x = setInterval(function() {
  
      // Get todays date and time
      var now = new Date().getTime();
      
      // Find the distance between now and the count down date
      var distance = testends - now;
      
      // Time calculations for days, hours, minutes and seconds
     
      var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
      var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
      var seconds = Math.floor((distance % (1000 * 60)) / 1000);
      
      // Output the result in an element with id="demo"
      document.getElementById("demo").innerHTML =hours + "h "
      + minutes + "m " + seconds + "s ";
      
      // If the count down is over, write some text 
      if (distance < 0) {
          clearInterval(x);
          document.getElementById("demo").innerHTML = "EXPIRED";
          document.getElementById("code_editor").disabled=true;
      }
  }, 1000);
  </script>

   <sec:csrfMetaTags/>
   
<script>


</script>
</head>
<body>

<!-- Use bulma.io css and use flexbox -->
<p id="demo"></p>


<div id="container">


<div id="lang" class="center">
 <select name="p_language" id="lang_selector">
  	<option name="java" value="" default>Java</option>
	<option name="js" value="">Javascript</option>
 </select>
</div>

<form:form action="/test?${_csrf.parameterName}=${_csrf.token}" modelAttribute="form" method ="post"  enctype="multipart/form-data">
<div class="columns  is-desktop center">
	<div class="column level-left">
	<spring:bind path="source_code">
		<form:textarea cols='100' rows='20' path="source_code" value="hello" onkeydown="if(event.keyCode===9){var v=this.value,s=this.selectionStart,e=this.selectionEnd;this.value=v.substring(0, s)+'\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;return false;}"></form:textarea>> 
	</spring:bind>
	</div>

	<div class="column level-right">
		<textarea id="display_result" cols='100' rows='20' disabled >${feedback}</textarea>
	</div>
</div>

<div class="level center">
	<div class="level-item">
		<input type="button" id="run_code" onclick="runCode()" value="run" /> 
	</div>
	<div class="level-item">
		<input type="submit" id="submit_code" value="submit" /> 
	</div>
</div>
</form:form>

</div>

</body>
</html>
