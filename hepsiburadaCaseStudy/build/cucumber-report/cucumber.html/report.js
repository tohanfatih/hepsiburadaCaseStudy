$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/hepsiburada.feature");
formatter.feature({
  "name": "Hepsiburada",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@hepsiburada"
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "Open with Chrome",
  "keyword": "* "
});
formatter.match({
  "location": "theinternet.CustomSteps.setUpChrome()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Hepsiburada case study",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@hepsiburada"
    }
  ]
});
formatter.step({
  "name": "Open Hepsiburada page",
  "keyword": "* "
});
formatter.match({
  "location": "theinternet.CustomSteps.openPage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Login with test user",
  "keyword": "* "
});
formatter.match({
  "location": "theinternet.CustomSteps.clickLogin()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Search product",
  "keyword": "* "
});
formatter.match({
  "location": "theinternet.CustomSteps.search()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Filter product",
  "keyword": "* "
});
formatter.match({
  "location": "theinternet.CustomSteps.filter()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Go to bottom of the list and select random product which in the last row",
  "keyword": "* "
});
formatter.match({
  "location": "theinternet.CustomSteps.scrollThenSelect()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Select lowest rated seller and add product to basket then check shopping cart",
  "keyword": "* "
});
formatter.match({
  "location": "theinternet.CustomSteps.showAllMerchants()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});