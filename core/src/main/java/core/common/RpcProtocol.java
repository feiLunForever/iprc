package core.common;

import lombok.Data;

import java.io.Serializable;

import static core.common.constants.RpcConstants.*;

@Data
public class RpcProtocol implements Serializable {

    private static final long serialVersionUID = 5359096060555795690L;

    private short magicNumber = MAGIC_NUMBER;

    private int contentLength;

    private byte[] content;

    public RpcProtocol(byte[] content) {
        this.contentLength = content.length;
        this.content = content;
    }
}
