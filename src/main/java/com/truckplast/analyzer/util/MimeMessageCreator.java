package com.truckplast.analyzer.util;


import com.truckplast.analyzer.constant.MimeMessageConstant;
import com.truckplast.analyzer.pojo.MailNotificationInfo;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static javax.mail.Message.RecipientType.TO;

@Slf4j
@UtilityClass
public class MimeMessageCreator {

    public MimeMessage getMimeMessage(MailNotificationInfo mailNotificationInfo) {

        Session session = Session.getInstance(new Properties(System.getProperties()));
        MimeMessage mimeMessage = new MimeMessage(session);
        MimeMultipart message = new MimeMultipart(MimeMessageConstant.SUBTYPE_MIXED);
        trySetDataToMimeMessage(mimeMessage, mailNotificationInfo, message, mailNotificationInfo.getFrom(), mailNotificationInfo.getTo());

        return mimeMessage;
    }

    private void trySetDataToMimeMessage(MimeMessage mimeMessage, MailNotificationInfo notification,
                                         MimeMultipart message, String from, String recipients) {

        try {

            mimeMessage.setSubject(notification.getSubject(), MimeMessageConstant.CHARSET_TYPE);
            mimeMessage.setFrom(from);
            mimeMessage.addRecipients(TO, recipients);
            trySetAttachmentAndTextToTheMessage(notification, message);
            mimeMessage.setContent(message);

        } catch (MessagingException e) {

            log.info("Error when to MimeMessage - from, to and file.");

            e.printStackTrace();
        }
    }

    private void trySetAttachmentAndTextToTheMessage(MailNotificationInfo notification, MimeMultipart message) {

        try {
            message.addBodyPart(tryGetMimeBodyPartWrapper(notification));

            if (notification.getFileInfoDto() != null) {
                getMimeBodyPartForAttachment(notification, message);
            }
        } catch (MessagingException e) {

            log.info("Error set to MimeMultipart - wrapper and attach.");

            e.printStackTrace();
        }
    }

    private MimeBodyPart tryGetMimeBodyPartWrapper(MailNotificationInfo notification) {

        try {

            MimeMultipart messageBody = new MimeMultipart(MimeMessageConstant.SUBTYPE_ALTERNATIVE);
            MimeBodyPart wrapper = new MimeBodyPart();
            MimeBodyPart htmlPart = new MimeBodyPart();

            htmlPart.setContent(notification.getMessage(), MimeMessageConstant.MIME_BODY_PART_CONTENT_PART);
            messageBody.addBodyPart(htmlPart);
            wrapper.setContent(messageBody);

            return wrapper;

        } catch (MessagingException e) {

            e.printStackTrace();
            return null;
        }
    }

    private void getMimeBodyPartForAttachment(MailNotificationInfo notification, MimeMultipart message) {

        String fullFileName = notification.getFileInfoDto().getFileName() + notification.getFileInfoDto().getExtension();

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        InputStream inputStream = new ByteArrayInputStream(notification.getFileInfoDto().getFileBytes());
        trySetDataToMimeBodyPartForFilePart(inputStream, messageBodyPart, fullFileName);
        tryAddBodyPart(message, messageBodyPart);

    }

    private void trySetDataToMimeBodyPartForFilePart(InputStream inputStream, MimeBodyPart messageBodyPart,
                                                     String fullName) {

        try {

            InputStreamDataSource inputStreamDataSource = new InputStreamDataSource(MimeMessageConstant.INPUT_STREAM_DATA_SOURCE_CONTENT_TYPE,
                    fullName,
                    inputStream);
            messageBodyPart.setDataHandler(new DataHandler(inputStreamDataSource));
            messageBodyPart.setFileName(MimeUtility.encodeText(inputStreamDataSource.getName()));

        } catch (MessagingException | NullPointerException | IOException e) {

            log.info("Error when to set data to MimeBodyPart for FilePart.");

            e.printStackTrace();
        }
    }

    private void tryAddBodyPart(MimeMultipart message, MimeBodyPart messageBodyPart) {

        try {

            message.addBodyPart(messageBodyPart);

        } catch (MessagingException e) {

            log.info("Error when adding MimeBodyPart to MimeMultipart.");

            e.printStackTrace();
        }
    }
}
