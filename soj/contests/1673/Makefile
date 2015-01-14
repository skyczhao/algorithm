
CXX = g++
CXXFLAGS = -g
OBJS = 1000.o 1001.o 1002.o 1003.o 1004.o
EXE = 1000 1001 1002 1003 1004

all: $(EXE)

1004: 1004.o
	$(CXX) $(CXXFLAGS) -o $@ $<

1003: 1003.o
	$(CXX) $(CXXFLAGS) -o $@ $<

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
