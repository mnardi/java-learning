
class Book implements Resource{
    @Override
    public void execute(String cmd)
    {
        System.out.println(" [*] Book executes: '" + cmd + "'");
    }
}
