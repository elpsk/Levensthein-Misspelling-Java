/**
 *
 * Levensthein words algorithm.
 * Take a string, split by space, and apply levensthein for every word.
 *
 * User: Alberto Pasca
 * Date: 03/12/13
 */

public class Levensthein
{
    public static void main(String[] args)
    {
        System.out.println(compareStrings("pippo", "pippa"));
        System.out.println(compareStrings("milano", "mialno"));
        System.out.println(compareStrings("alberto rulez", "rulez alberto"));
        System.out.println(compareStrings("milano", "qwerty"));
        System.out.println(compareStrings("abc", "def"));
        System.out.println(compareStrings("torino", "milano"));

        System.out.println(compareStrings("Museo d'arte contemporanea - Castello di Rivoli", "Castello di Rivoli Museo d'arte contemporanea"));
    }



    public static float compareStrings ( String first, String last )
    {
        float averageSmallestDistance = 0.0f;
        float smallestDistance;
        float distance;

        first = first.replaceAll("\n", "");
        last  = last.replaceAll ("\n", "");

        String[] firstCks = first.split (" ");
        String[] lastCks  = last.split  (" ");

        String firstToken = "";
        String lastToken  = "";
        for ( int i=0; i<firstCks.length; i++ )
        {
            firstToken = firstCks[i];
            smallestDistance = 99999999.0f;

            for ( int j=0; j<lastCks.length; j++ )
            {
                lastToken = lastCks[j];

                distance = compareWord (firstToken, lastToken);
                if ( ( distance < smallestDistance ) )
                    smallestDistance = distance;
            }

            averageSmallestDistance += smallestDistance;
        }

        return averageSmallestDistance / firstCks.length;
    }


    private static float compareWord(String firstToken, String lastToken)
    {
        firstToken = firstToken.toLowerCase();
        lastToken  = lastToken.toLowerCase();

        int[] d;
        int k, i, j, cost, distance;
        int n = firstToken.length();
        int m = lastToken.length();

        if ( n++ != 0 && m++ != 0 )
        {
            d = new int[m*n];

            for ( k = 0; k < n; k++ ) d[k] = k;
            for ( k = 0; k < m; k++ ) d[ k * n ] = k;

            for ( i = 1; i < n; i++ )
                for( j = 1; j < m; j++ )
                {
                    if ( firstToken.charAt(i-1) == lastToken.charAt(j-1) ) cost = 0;
                    else cost = 1;

                    d[ j * n + i ] = smallestOf(d [(j-1)*n+i] + 1, d[j*n+i-1] +  1, d[(j-1)*n+i-1] + cost );
                }

            distance = d[n*m-1];
            return distance;
        }

        return 0.0f;
    }

    private static int smallestOf( int a, int b, int c )
    {
        int min = a;
        if ( b < min ) min = b;
        if ( c < min ) min = c;

        return min;
    }
}


