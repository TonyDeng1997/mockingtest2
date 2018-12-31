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

<!-- The followings are code mirror -->
<link rel=stylesheet href="resources/code-mirror-5.40.0/doc/docs.css">
<link rel="stylesheet" href="resources/code-mirror-5.40.0/lib/codemirror.css">
<link rel="stylesheet" href="resources/code-mirror-5.40.0/addon/hint/show-hint.css">
<script src="resources/code-mirror-5.40.0/lib/codemirror.js"></script>
<script src="resources/code-mirror-5.40.0/addon/hint/show-hint.js"></script>
<script src="resources/code-mirror-5.40.0/addon/hint/anyword-hint.js"></script>
<script src="resources/code-mirror-5.40.0/mode/javascript/javascript.js"></script>



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

body {
        overflow: hidden;
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



<sec:csrfMetaTags/>
   
<script></script>
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

<!--<div id="editor"></div>-->
<!--   <div id="editor2">${feedback}</div>-->

<form:form action="/runcode?${_csrf.parameterName}=${_csrf.token}" modelAttribute="form" method ="post"  enctype="multipart/form-data">
<!--  <div class="columns  is-desktop center">-->
	<div class="column level-left">
	<spring:bind path="source_code">
		<form:textarea cols='100' rows='20' path="source_code" value="hello" id="code" name="code"  placeholder="public class Solution {}" ></form:textarea>
	</spring:bind>
	</div>

	<div class="column level-right">
		<textarea id="display_result" cols='100' rows='20' disabled id="feedback" >${feedback}</textarea>
	</div>
<!--   </div>-->

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


<script>

function disableF5(e) { if ((e.which || e.keyCode) == 116) e.preventDefault(); };
$(document).on("keydown", disableF5);
</script>

<script>

  function startTimer(duration, display) {
var timer = duration, minutes, seconds;
var clearint=setInterval(function () {
    minutes = parseInt(timer / 60, 10)
    seconds = parseInt(timer % 60, 10);

    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    display.textContent = minutes + " " + " " + seconds;

    if (--timer < 0) {
      timer = -1;
      display.textContent = "Expired";
      localStorage.clear();
      clearInterval(clearint);
      $("#code")
      
    }
  console.log(parseInt(seconds))
  
  window.localStorage.setItem("seconds",seconds)
  window.localStorage.setItem("minutes",minutes)
  window.localStorage.setItem("timer",timer)
}, 1000);



}


window.onload = function () {
  sec  = parseInt(window.localStorage.getItem("seconds"))
  min = parseInt(window.localStorage.getItem("minutes"))
  timer=parseInt(window.localStorage.getItem("timer"))

  if(timer>0){
    var fiveMinutes = (parseInt(min*60)+sec);
  }else{
    var fiveMinutes = 30;
  }
    // var fiveMinutes = 60 * 5;
  display = document.querySelector('#demo');
  startTimer(fiveMinutes, display);
};


</script>
  
  <script>
      CodeMirror.commands.autocomplete = function(cm) {
        cm.showHint({hint: CodeMirror.hint.anyword});
      }
      var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
        lineNumbers: true,
        extraKeys: {"Ctrl-Space": "autocomplete"}
      });
      if (window.performance) {
  console.info("window.performance works fine on this browser");
}
  if (performance.navigation.type == 1) {
    alert( "U just RELOADAED!" );
  } else {
    console.info( "This page is not reloaded");
    localStorage.clear();
  }
      
      
    </script>
</body>
</html>
