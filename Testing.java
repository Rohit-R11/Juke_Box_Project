package jukebox;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class Testing {
	@Before
	public void  setup() {

	}
	@Test
	public void test1() throws SQLException  {
		List<ModelSongs> numberOfSongs=Function.SongsIntoList();
		assertEquals(6,numberOfSongs.size());
		assertNotNull(numberOfSongs);
	}
	
	@Test
	public void test2() throws SQLException {
		List<ModelSongs> numberOfPodcasts=Function.PodcastIntoList();
		assertEquals(3,numberOfPodcasts.size());
		assertNotNull(numberOfPodcasts);
	}
	
	@Test
	public void test3() {
		
	}
	
	
}
