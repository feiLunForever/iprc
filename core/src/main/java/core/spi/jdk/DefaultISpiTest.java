package core.spi.jdk;

public class DefaultISpiTest implements ISpiTest{

    @Override
    public void doTest() {
        System.out.println("执行测试方法");
    }

}