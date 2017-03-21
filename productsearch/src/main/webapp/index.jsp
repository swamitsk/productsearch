<!DOCTYPE html>
<html lang="en" ng-app="productFinder">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Product Search App</title>
    <!-- Bootstrap css and my own css -->
    <link rel="stylesheet" 
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    <link rel="stylesheet" href="css/style.css">

</head>

<body>
   
    <div class="container">
        <div class="page-header">
            <h1>Product Search </h1>
        </div>

        <div ng-controller="productcontroller as pctrl">
        
	<form class="form-inline well well-sm clearfix">
		<span class="glyphicon glyphicon-search"></span>
		 
		 
		  <select ng-model=pctrl.currentSelection  ng-options='category for category in pctrl.categories'></select>
<button ng-click="pctrl.RetrieveObjectWeight()">Get Weight</button>
		
      </form>   
      <table ng-show="pctrl.objectContent.length">
          <tr>
    <th>Index</th>
    <th>Product Total Weight(kg)</th>
    <th>Title</th>
    <th>Width</th>
    <th>Height </th>
    <th>Length</th>
         <tr ng-repeat="item in pctrl.objectContent">
                <td width="15%">{{$index+1}}</td>
                <td width="15%">{{ item.totalWeight[0] }}</td>
                <td width="15%">{{ item.title }}</td>
                  <td width="20%">{{ item.size.width }}</td>
                  <td width="30%">{{ item.size.height }}</td>
                  <td width="30%">{{ item.size.length }}</td>
               
        </tr>
        </table>
        <div>
    Average: {{ pctrl.average }}
		</div>
        </div>

    </div>
     <!-- third party js -->
     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.js"></script> 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- application scripts -->

    <script type="text/javascript" src="js/app.js"></script>
    <script type="text/javascript" src="js/controller/productcontroller.js"></script>


</body>
</html>