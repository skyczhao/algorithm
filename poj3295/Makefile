
CC = g++
CFLAGS = -g
OBJS = main.o

main: $(OBJS)
	$(CC) $(CFLAGS) -o main $(OBJS)

$(OBJS): %.o: %.c
	$(CC) $(CFLAGA) -c $<

.PONY: clean
clean:
	rm main $(OBJS)
