
CXX = g++
CFLAGS = -g
OBJS = main.o

main: $(OBJS)
	$(CXX) $(CFLAGS) -o main $(OBJS)

$(OBJS): %.o: %.cpp
	$(CXX) $(CFLAGS) -c $<

.PONY: clean
clean:
	rm main $(OBJS)
