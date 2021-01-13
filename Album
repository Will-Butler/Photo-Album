import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.swing.*;

/*
 * This class defines and launches the GUI interface which includes
 * a photograph, its filename, a previous and next button to go through
 * multiple photos, and a group of rating buttons to set the rating of the photo
 */
//Used information from following link for general guide through out the creation of this class:  
//https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html.
public class PhotoViewer extends JFrame{
	
	private PhotoContainer imageLibrary; //creates a photo container to hold the photos
	private JPanel ratingButtons = new JPanel(); //creates panel to hold the rating buttons
	private JButton next = new JButton("Next");; //next button for moving to next photo in the container
	private JButton previous = new JButton("Previous");; //previous button for moving to previous button in container
	private JRadioButton rating1 = new JRadioButton("1"); //button to change rating of photo to 1
	private JRadioButton rating2 = new JRadioButton("2"); //button to change rating of photo to 2
	private JRadioButton rating3 = new JRadioButton("3"); //button to change rating of photo to 3
	private JRadioButton rating4 = new JRadioButton("4"); //button to change rating of photo to 4
	private JRadioButton rating5 = new JRadioButton("5"); //button to change rating of photo to 5
	private JLabel photoName = new JLabel(); //label for name of photo
	private JLabel photo = new JLabel(); //label to store photo
	private int picIndex = 0; //int to track the index of the photos in the image library


	/*
	 * getImageLibrary has no parameter and returns 
	 * the reference to the objects image library
	 */
	public PhotoContainer getImageLibrary() {
		return this.imageLibrary;
	}
	/*
	 * setImageLibrary takes a PhotoContainer object as a parameter and sets
	 * it equal to the the objects imageLibrary.
	 */
	public void setImageLibrary(PhotoContainer imageLibrary) {
		this.imageLibrary = imageLibrary;
	}
	
	/*
	 * this method sets the size of the window, adds the components
	 * to the window using the addComponentsToPane method, ensures
	 * the gui operation will exit once the window closes, fits the
	 * contents into the window, and makes the window visible 
	 */
	private void createAndShowGUI() {
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		this.addComponentsToPane(this.getContentPane());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(width, height);
		this.setVisible(true);
	}
	/*
	 * this method will create a variety of components to add
	 * to the given content Container in an organized manner 
	 */
	public void addComponentsToPane(Container pane) {
		pane.add(this.next, BorderLayout.LINE_END); //vocabulary for layout positioning obtained from https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
		pane.add(this.previous, BorderLayout.LINE_START);
		
		//Use an index variable to identify a photo from imageLibrary 
		//to be put into an image icon which will be stored in a JLabel. 
		//This JLabel displays the photo in the center of the pane.
		ImageIcon icon = new ImageIcon(this.imageLibrary.getPhotos().get(picIndex).getImageData());
		photo.setIcon(icon);		
		pane.add(this.photo, BorderLayout.CENTER);
		
		
		//add photo filename to top of pane
		this.photoName.setText(this.imageLibrary.getPhotos().get(picIndex).getFilename().substring(7));
		pane.add(this.photoName, BorderLayout.PAGE_START);
		this.photoName.setHorizontalAlignment(SwingConstants.CENTER); //information on setting label alignment found at https://stackoverflow.com/questions/6810581/how-to-center-the-text-in-a-jlabel
		
		//put rating buttons in a group
		ButtonGroup group = new ButtonGroup();
		group.add(this.rating1);
		group.add(this.rating2);
		group.add(this.rating3);
		group.add(this.rating4);
		group.add(this.rating5);
		//add buttons to panel with flow layout, then add panel to pane
		this.ratingButtons.add(this.rating1);
		this.ratingButtons.add(this.rating2);
		this.ratingButtons.add(this.rating3);
		this.ratingButtons.add(this.rating4);
		this.ratingButtons.add(this.rating5);
		pane.add(this.ratingButtons, BorderLayout.PAGE_END);
		//set rating button of the current photo to the photos current rating
		int currentRating = this.imageLibrary.getPhotos().get(picIndex).getRating();
		if(currentRating == 1) {
			this.rating1.setSelected(true);
		}
		else if(currentRating == 2) {
			this.rating2.setSelected(true);
		}
		else if(currentRating == 3) {
			this.rating3.setSelected(true);
		}
		else if(currentRating == 4) {
			this.rating4.setSelected(true);
		}
		else if(currentRating == 5) {
			this.rating5.setSelected(true);
		}
		
		//Action listener for next button
		class NextButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(picIndex != PhotoViewer.this.imageLibrary.getPhotos().size() - 1) {
					picIndex += 1;
				}
				else {
					picIndex = 0;
				}
				//set image
				ImageIcon icon = new ImageIcon(PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).getImageData());
				photo.setIcon(icon);
				//set rating
				int currentRating = PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).getRating();
				if(currentRating == 1) {
					PhotoViewer.this.rating1.setSelected(true);
				}
				else if(currentRating == 2) {
					PhotoViewer.this.rating2.setSelected(true);
				}
				else if(currentRating == 3) {
					PhotoViewer.this.rating3.setSelected(true);
				}
				else if(currentRating == 4) {
					PhotoViewer.this.rating4.setSelected(true);
				}
				else if(currentRating == 5) {
					PhotoViewer.this.rating5.setSelected(true);
				}
				//set filename
				PhotoViewer.this.photoName.setText(PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).getFilename().substring(7));

			}
		}
		//add listener to the JButton
		this.next.addActionListener(new NextButtonListener());
		
		//Action listener for previous button
		class PreviousButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(picIndex != 0) {
					picIndex -= 1;
				}
				else {
					picIndex = PhotoViewer.this.imageLibrary.getPhotos().size() - 1;
				}
				//set image
				ImageIcon icon = new ImageIcon(PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).getImageData());
				photo.setIcon(icon);
				//set rating
				int currentRating = PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).getRating();
				if(currentRating == 1) {
					PhotoViewer.this.rating1.setSelected(true);
				}
				else if(currentRating == 2) {
					PhotoViewer.this.rating2.setSelected(true);
				}
				else if(currentRating == 3) {
					PhotoViewer.this.rating3.setSelected(true);
				}
				else if(currentRating == 4) {
					PhotoViewer.this.rating4.setSelected(true);
				}
				else if(currentRating == 5) {
					PhotoViewer.this.rating5.setSelected(true);
				}
				//set filename
				PhotoViewer.this.photoName.setText(PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).getFilename().substring(7));
			}
		}
		this.previous.addActionListener(new PreviousButtonListener());
		
		//Rating Button Action Listeners: set rating of photo to selected number
		//and displays the selected choice in the associated rating button
		
		//Action listener for rating button 1
		class RatingButton1Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).setRating(1);
				PhotoViewer.this.rating1.setSelected(true);
			}
		}	
		this.rating1.addActionListener(new RatingButton1Listener());
		
		//Action listener for rating button 2
		class RatingButton2Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).setRating(2);
				PhotoViewer.this.rating2.setSelected(true);
			}
		}	
		this.rating2.addActionListener(new RatingButton2Listener());

		//Action listener for rating button 3
		class RatingButton3Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).setRating(3);
				PhotoViewer.this.rating3.setSelected(true);
			}
		}	
		this.rating3.addActionListener(new RatingButton3Listener());

		//Action listener for rating button 4
		class RatingButton4Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).setRating(4);
				PhotoViewer.this.rating4.setSelected(true);
			}
		}	
		this.rating4.addActionListener(new RatingButton4Listener());

		//Action listener for rating button 5
		class RatingButton5Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				PhotoViewer.this.imageLibrary.getPhotos().get(picIndex).setRating(5);
				PhotoViewer.this.rating5.setSelected(true);
			}
		}	
		this.rating5.addActionListener(new RatingButton5Listener());

	}
	
	public static void main(String[] args) {
		
		PhotoViewer myViewer = new PhotoViewer();
		//Convert all of the downloaded images into photo objects and place them into the image library instance
		Photo p1 = new Photo("images/clouds.jpeg", "From a plane.", "2020-03-06", 5);
		Photo p2 = new Photo("images/sun.jpeg", "Also from a plane.", "2020-03-06", 5);
		Photo p3 = new Photo("images/dog.jpeg", "Frog or dog?", "2020-03-08", 5);
		Photo p4 = new Photo("images/ski.jpeg", "UTAH!", "2019-12-28", 5);
		Photo p5 = new Photo("images/water.jpeg", "Placid", "2020-03-10", 5);
		
		//use loadImageData() to convert the photos into buffered photos
		p1.loadImageData("images/clouds.jpeg");
		p2.loadImageData("images/sun.jpeg");
		p3.loadImageData("images/dog.jpeg");
		p4.loadImageData("images/ski.jpeg");
		p5.loadImageData("images/water.jpeg");

		//Create an image library within the myViewer object to hold the photos
		myViewer.setImageLibrary(new Library("Test Library", 1));
		
		//Add the photos to the image library
		myViewer.getImageLibrary().addPhoto(p1);
		myViewer.getImageLibrary().addPhoto(p2);
		myViewer.getImageLibrary().addPhoto(p3);
		myViewer.getImageLibrary().addPhoto(p4);
		myViewer.getImageLibrary().addPhoto(p5);

		//sort the photos using the previously written compareTo() method
		Collections.sort(myViewer.getImageLibrary().getPhotos());
		
		//launch GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				myViewer.createAndShowGUI();
			}
		});
	}

}
