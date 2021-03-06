package at.borkowski.spicej;

import java.io.InputStream;
import java.io.OutputStream;

import at.borkowski.spicej.streams.DelayedInputStream;
import at.borkowski.spicej.streams.RateLimitInputStream;
import at.borkowski.spicej.streams.RateLimitOutputStream;
import at.borkowski.spicej.ticks.TickSource;

/**
 * Provides helper functions for easy shaping of streams.
 */
public class Streams {
   private Streams() {}

   /**
    * Creates a byte-rate-limited {@link InputStream}. See
    * {@link RateLimitInputStream#RateLimitInputStream(InputStream, TickSource, int, int)}
    * for detailed information.
    * 
    * @param base
    *           The raw (underlying) {@link InputStream}
    * @param tickSource
    *           The source of ticks
    * @param bytesPerTick
    *           (see
    *           {@link RateLimitInputStream#RateLimitInputStream(InputStream, TickSource, int, int)}
    *           )
    * @param prescale
    *           (see
    *           {@link RateLimitInputStream#RateLimitInputStream(InputStream, TickSource, int, int)}
    *           )
    * @return the resulting input stream
    */
   public static RateLimitInputStream limitRate(InputStream base, TickSource tickSource, int bytesPerTick, int prescale) {
      return new RateLimitInputStream(base, tickSource, bytesPerTick, prescale);
   }

   /**
    * Creates a byte-rate-limited {@link OutputStream}. See
    * {@link RateLimitOutputStream#RateLimitOutputStream(OutputStream, TickSource, int, int)}
    * for detailed information.
    * 
    * @param base
    *           The raw (underlying) {@link OutputStream}
    * @param tickSource
    *           The source of ticks
    * @param bytesPerTick
    *           (see
    *           {@link RateLimitOutputStream#RateLimitOutputStream(OutputStream, TickSource, int, int)}
    *           )
    * @param prescaler
    *           (see
    *           {@link RateLimitOutputStream#RateLimitOutputStream(OutputStream, TickSource, int, int)}
    *           )
    * @return the resulting output stream
    */
   public static RateLimitOutputStream limitRate(OutputStream base, TickSource tickSource, int bytesPerTick, int prescaler) {
      return new RateLimitOutputStream(base, tickSource, bytesPerTick, prescaler);
   }

   /**
    * Creates a delayed {@link InputStream}. See
    * {@link DelayedInputStream#DelayedInputStream(TickSource, InputStream, long, int)}
    * for detailed information.
    * 
    * @param base
    *           The raw (underlying) {@link InputStream}.
    * @param tickSource
    *           The source of ticks
    * @param delay
    *           The delay in ticks to add to the stream
    * @param bufferSize
    *           The buffer size to use (see
    *           {@link DelayedInputStream#DelayedInputStream(TickSource, InputStream, long, int)}
    *           )
    * @return the resulting input stream
    */
   public static DelayedInputStream addDelay(InputStream base, TickSource tickSource, long delay, int bufferSize) {
      return new DelayedInputStream(tickSource, base, delay, bufferSize);
   }

}
