
CXX = g++
CXXFLAGS = -g
OBJS = 1000.o 1001.o 1002.o
EXE = 1000 1001 1002

all: $(EXE)

1002: 1002.o
	$(CXX) $(CXXFLAGS) -o $@ $<

1001: 1001.o
	$(CXX) $(CXXFLAGS) -o $@ $<

1000: 1000.o
	$(CXX) $(CXXFLAGS) -o $@ $<

$(OBJS): %.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $<

.PHONY: clean
clean:
	rm $(OBJS) $(EXE)
