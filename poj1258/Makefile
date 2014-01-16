
CXX = g++
CXXFLAGS = -g
OBJS = main.o

main: $(OBJS)
	$(CXX) $(CXXFLAGS) -o main $(OBJS)

$(OBJS): %.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $<

.PHONY: clean
clean:
	rm main $(OBJS)
