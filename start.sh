#!/bin/bash

# CoffeeWatch - Unified Startup Script
# This script builds and starts both the Backend (Spring Boot) and Frontend (Angular)

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${BLUE}=== CoffeeWatch Startup System ===${NC}"

# 1. Check for Prerequisites
command -v java >/dev/null 2>&1 || { echo -e "${RED}Error: Java is not installed.${NC}" >&2; exit 1; }
command -v npm >/dev/null 2>&1 || { echo -e "${RED}Error: npm is not installed.${NC}" >&2; exit 1; }

# 2. Build and Start Backend
echo -e "${GREEN}[1/3] Preparing Backend...${NC}"
cd backend
# Compile (using Maven wrapper if available, otherwise global mvn)
if [ -f "./mvnw" ]; then
    ./mvnw clean compile -DskipTests
else
    mvn clean compile -DskipTests
fi

echo -e "${GREEN}[2/3] Starting Backend in background...${NC}"
# Run the application. We use the Main class or Spring Boot run
# Redirecting output to a log file to keep the terminal clean
nohup mvn spring-boot:run > ../backend.log 2>&1 &
BACKEND_PID=$!
echo -e "${BLUE}Backend PID: $BACKEND_PID (Logs: backend.log)${NC}"

# 3. Build and Start Frontend
cd ../frontend
echo -e "${GREEN}[3/3] Preparing Frontend...${NC}"
if [ ! -d "node_modules" ]; then
    echo "Installing frontend dependencies (this may take a minute)..."
    npm install --silent
fi

echo -e "${GREEN}Starting Frontend...${NC}"
echo -e "${BLUE}Application will be available at: http://localhost:4200${NC}"

# Run frontend (this will stay in foreground)
npm start

# Cleanup on exit (kill backend when frontend is stopped)
trap "kill $BACKEND_PID" EXIT
