/**
 * Print out the sample UI
 *
 * @author Team 3
 * @version 1.0
 */
public class Text
{
    private String leftSpace(String input)
    {
        int length = 71;
        String leftSpace = " ";
        String rightSpace = " ";
        while (leftSpace.length() + rightSpace.length() < (length - input.length()))
        {
            leftSpace = leftSpace + " ";
            if (leftSpace.length() + rightSpace.length() < (length - input.length()))
                rightSpace = rightSpace + " ";
        }
        return leftSpace;
    }
    
    private String rightSpace(String input)
    {
        int length = 71;
        String leftSpace = " ";
        String rightSpace = " ";
        while (leftSpace.length() + rightSpace.length() < (length - input.length()))
        {
            leftSpace = leftSpace + " ";
            if (leftSpace.length() + rightSpace.length() < (length - input.length()))
                rightSpace = rightSpace + " ";
        }
        return rightSpace;
    }
    
    public void title(String title, String type, String user)
    {
        String displayUser = type + "  : " + user;
        String ls1 = leftSpace(title);
        String rs1 = rightSpace(title);
        String ls2 = leftSpace(displayUser);
        String rs2 = rightSpace(displayUser);
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("+                                                                       +");
        System.out.println("+" + ls1 + title + rs1 + "+");
        System.out.println("+" + ls2 + displayUser + rs2 + "+");
        System.out.println("+                                                                       +");
        System.out.println("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }
    
    public void displayInfo(String info)
    {
        int length = 71;
        String space = " ";
        while (space.length() < (length - info.length()))
        {
            space = space + " ";
        }
        System.out.println("+" + info + space + "+");
    }
    
    public void displayHall()
    {
        System.out.println("+Hall information displays here                                         +");
        System.out.println("+                                                                       +");
        System.out.println("+                                                                       +");
        System.out.println("+                                                                       +");
        System.out.println("+-----------------------------------------------------------------------+");
    }
}
