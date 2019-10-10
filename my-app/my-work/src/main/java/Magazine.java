
class Magazine implements Resource{
    @Override
    public void execute(String cmd)
    {
        System.out.println(" [*] Magazine executes: '" + cmd + "'");
    }
}
