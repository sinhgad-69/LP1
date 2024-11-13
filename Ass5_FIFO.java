import java.util.Scanner;

class FIFOPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Get the number of frames from the user
        System.out.print("Enter the number of frames: ");
        int frameCount = sc.nextInt();
        int[] frames = new int[frameCount]; // Array to store frames
        
        // Initialize frames with -1 to indicate empty slots
        for (int i = 0; i < frameCount; i++) {
            frames[i] = -1;
        }
        
        // Get the number of pages from the user
        System.out.print("Enter the number of pages: ");
        int pageCount = sc.nextInt();
        int[] pages = new int[pageCount];
        
        // Get each page number
        System.out.println("Enter the page numbers: ");
        for (int i = 0; i < pageCount; i++) {
            pages[i] = sc.nextInt();
        }

        int hits = 0;       // Counts page hits
        int faults = 0;     // Counts page faults
        int pointer = 0;    // Points to the next frame to replace (FIFO order)

        // Process each page
        for (int page : pages) {
            boolean hit = false;

            // Check if the page is already in one of the frames
            for (int frame : frames) {
                if (frame == page) {
                    hit = true;
                    hits++; // Page hit found
                    break;
                }
            }

            // If the page is not in the frames, replace the oldest one (FIFO)
            if (!hit) {
                frames[pointer] = page;       // Replace frame at the pointer
                pointer = (pointer + 1) % frameCount; // Move pointer in a circular way
                faults++;
            }
        }

        // Print results
        System.out.println("Total Hits: " + hits);
        System.out.println("Total Faults: " + faults);
        System.out.println("Hit Ratio: " + (float) hits / pageCount);

        sc.close(); // Close the Scanner to release resources
    }
}
