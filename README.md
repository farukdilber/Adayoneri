				String fileName = fname;
				DataSource source = new FileDataSource(filePath + fileName);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setContent(fileName, "text/plain; charset=UTF-8");
				messageBodyPart.setFileName(fileName);
				multipart.addBodyPart(messageBodyPart);
