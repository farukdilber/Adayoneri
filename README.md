				else {
					String strMsg = getTextOfElement(By.xpath("//*[@id=\"screenarea\"]/tbody/tr[25]/td[2]/a")).trim();
					if(strMsg.contains("Baş.tarihi ödenen vade tarihine eşit ve küçük olamaz...(MSH0786)             ")) {

					String hataGun = getTextOfElement(By.xpath("//*[@id=\"screenarea\"]/tbody/tr[19]/td[5]/a"), 0);
					String hataAy = getTextOfElement(By.xpath("//*[@id=\"screenarea\"]/tbody/tr[19]/td[7]/a"), 0);
					String hataYil = getTextOfElement(By.xpath("//*[@id=\"screenarea\"]/tbody/tr[19]/td[9]/a"), 0);	
					sendKeys(By.id("inpR12C43L2.id"), hataGun);
					sendKeys(By.id("inpR12C46L2.id"), hataAy);
					sendKeys(By.id("inpR12C49L4.id"), hataYil);
					as400PressEnter();
					if(strMsg.contains("Baş.tarihi ödenen vade tarihine eşit ve küçük olamaz...(MSH0786)             ")) {
						//çalışma günü baz al 1 arttır
					}
					
					}
				}
