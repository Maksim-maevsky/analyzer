package com.truckplast.analyzer.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.search.FlagTerm;

@UtilityClass
public class MailUtil {

    @SneakyThrows
    public FlagTerm getFlagTerm(Folder folder) {

        folder.open(Folder.READ_WRITE);
        Flags seen = new Flags(Flags.Flag.SEEN);

        return new FlagTerm(seen, false);
    }
}
