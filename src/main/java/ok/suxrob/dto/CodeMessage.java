package ok.suxrob.dto;

import ok.suxrob.enums.CodeMessageType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class CodeMessage {
    private CodeMessageType type=CodeMessageType.MESSAGE;
    private SendMessage sendMessage;
    private EditMessageText editMessageText;

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public EditMessageText getEditMessageText() {
        return editMessageText;
    }

    public void setEditMessageText(EditMessageText editMessageText) {
        this.editMessageText = editMessageText;
    }

    public CodeMessageType getType() {
        return type;
    }

    public void setType(CodeMessageType type) {
        this.type = type;
    }
}
