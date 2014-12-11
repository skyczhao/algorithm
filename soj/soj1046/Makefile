
CC = g++
CFLAGS = -g
OBJS = main.o

main: $(OBJS)
	$(CC) $(CFLAGS) -o $@ $<

$(OBJS): %.o: %.cpp
	$(CC) $(CFLAGS) -c $<

.PHONY: clean
clean:
	rm main $(OBJS)
