package com.dge.utility;
import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.SubjectTerm;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class FetchLinkMail 
{
	public static String checkLinkMail(String subjectToFind)
	{
            //  String subject = "You have been enrolled in POSH: Prevention of Sexual Harassment"; // change this
               String link = "";
        try {
            link = fetchLinkFromEmail(subjectToFind);
            if (link != null) {
                System.out.println("Extracted Link: " + link);
            } else {
                System.out.println("No link found in the email.");
                link = "No";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return link;
    }

    public static String fetchLinkFromEmail(String subjectToFind) throws Exception {
    	 String host = "imap.gmail.com";
         String username = "malinim282@gmail.com";
         String password = "wnip fuql eskh lgqd";

    	// Set up properties for IMAP
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        Session session = Session.getInstance(properties);

        Store store = session.getStore();
        store.connect(host, username, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // Search for email with matching subject
        Message[] messages = inbox.search(new SubjectTerm(subjectToFind));

        for (Message message : messages) {
            String content = getTextFromMessage(message);

            // Regex to find URL
            Pattern pattern = Pattern.compile("https?://\\S+");
            Matcher matcher = pattern.matcher(content);

            if (matcher.find()) {
                return matcher.group(); // return the first found URL
            }
        }

        inbox.close(false);
        store.close();
        return null;
    }

    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < mimeMultipart.getCount(); i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            }
        }
        return result.toString();
    }
}
