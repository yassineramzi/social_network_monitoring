# Social Network Monitoring

## Overview
Social Network Monitoring is an application for monitoring social network platforms like YouTube, Facebook, and Twitter. The system collects, processes, and analyzes social media data to provide insights.

## Architecture

### Components
1. **Backend (Java Spring Boot)**: Handles data processing, interaction with social media APIs, business logic, and database operations.
2. **Frontend (Angular)**: Provides a user-friendly interface for visualizing collected data and managing monitoring tasks.
3. **Database**: Stores collected data, user information, and application configurations.
5. **Scheduler (Quartz Scheduler)**: Periodically triggers tasks for fetching data from social media APIs.

### Deployment
- **Docker and Docker Compose** are used for containerizing and deploying the application, providing easy setup and scaling.

## Functional Workflow

### Data Collection
1. **User Configuration**: Users set up monitoring tasks by specifying keywords, channels, or pages to monitor.
2. **Task Scheduling**: The scheduler triggers data collection jobs at defined intervals.
3. **API Integration**: The backend fetches data from social media platforms using their respective APIs.

### Data Processing
1. **Data Filtering**: Raw data is filtered and processed for relevant insights.
2. **Storage**: Processed data is stored in the database.

### Data Visualization
1. **Frontend Interface**: The Angular-based frontend allows users to visualize trends, perform searches, and view detailed analytics.
2. **Notification System**: Alerts and notifications are sent based on predefined rules or detected anomalies.

## Features
- **Platform Monitoring**: Monitor multiple social networks in real time.
- **Keyword Tracking**: Track keywords, hashtags, or specific channels.
- **Data Visualization**: Graphs and charts to analyze data.
- **Alerting**: Customizable alerts for trends or specific keywords.
  
## Technology Stack
- **Backend**: Java, Spring Boot
- **Frontend**: Angular, TypeScript, SCSS
- **Database**: MySQL/PostgreSQL
- **Deployment**: Docker, Docker Compose

## Setup and Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yassineramzi/social_network_monitoring.git
