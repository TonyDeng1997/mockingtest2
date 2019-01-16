<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- The following are jquery and bulma -->
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

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



#editor {
       position: relative !important;
       border: 1px solid lightgray;
       margin: auto;
       height: 300px;
       width: 80%;
       
}
  
#editor2 {
       position: relative !important;
       border: 1px solid lightgray;
       margin: auto;
       height: 300px;
       width: 80%;
       
}   
</style>

<script type="text/javascript" >
   function preventBack(){
   		window.history.forward();
   }
   setTimeout("preventBack()", 0);
   window.onunload=function(){
   	null
   };
</script>

<sec:csrfMetaTags/>
</head>


<body>

<!-- Use bulma.io css and use flexbox -->
<p id="demo"></p>

<div id="container">

<div id="lang" class="center" >
 <select name="p_language" id="lang_selector"  onchange="changefunction()">
  	<option name="java" value="java" default>Java</option>
	<option name="js" value="javascript">Javascript</option>
	<option name="python" value="python">python</option>
 </select>
</div>

<form:form action="/runcode?${_csrf.parameterName}=${_csrf.token}" 
	modelAttribute="form" method ="post" enctype="multipart/form-data">

	<div class="column level-left">
	<spring:bind path="source_code">
		<form:textarea id="code" cols='100' rows='20' path="source_code" placeholder="" ></form:textarea>
	</spring:bind>
	</div>

	<div class="column level-right">
		<textarea id="display_result" cols='100' rows='20' disabled id="output" >${output}</textarea>
	</div>

<div class="level center">
	<div class="level-item">
		<input type="submit" id="run_code" onclick="runCode()" value="run" /> 
	</div>
	<div class="level-item">
		<input type="submit" id="submit_code" value="submit" onclick="myFunction()"/> 
	</div>
</div>
</form:form>
</div>

<script src= "resources/javascript/timer.js" ></script>

<%@ include file="codeMirrorInit.html" %>

</body>
</html>
