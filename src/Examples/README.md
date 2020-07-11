driver.get("");
driver.navigate().to("");
driver.navigate().back();
driver.navigate().back();

driver.getTitle();
driver.getCurrentUrl();

driver.findElement(By.id("")).getText();
driver.findElement(By.name("")).getText();
driver.findElement(By.tagName("")).getText();
driver.findElement(By.className("")).getText();
driver.findElement(By.cssSelector("body > h4")).getText();
driver.findElement(By.linkText("")).click();
driver.findElement(By.partialLinkText("")).click();

driver.findElements(By.tagName("input")).get(3).click();
