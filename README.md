## DOCS

- **C4**: [Diagrama C4 en Miro](https://miro.com/app/board/uXjVLFFXaSM=/)

- **DOC**: [Documento de especificaciones](https://docs.google.com/document/d/1nfEoqsqFpgBnGMgwS9TZur1GalcxmrG4PZv4fmRaogQ/edit?tab=t.0#heading=h.fk65gu7wgxsc)

- **LCS Steps**: [Pasos LCS](https://drive.google.com/file/d/15WZJap6Dnv0Bw3hPiEAc9HJiBbkMxKoT/view?usp=sharing)


# Text Similarity Comparator
This project is a client-server application designed to compare texts and calculate their similarity. The tool also identifies misspelled words, providing a detailed analysis. It is useful for checking duplicate content, performing text reviews or analyzing similarities between documents.

## Key features
Text comparison using similarity analysis based on Longest Common Subsequence (LCS).
Identification of misspelled words between two texts.
Client interface that allows file selection and displays the results clearly.
Efficient server that performs text processing in a multithreaded environment.
Generation of a summary with detailed results, including similarity percentage and misspelled words.

## Project Structure

```
├── Client/
│ ├─── Controller/ # Client main logic.
│ ├─── Model/ # Client data models.
│ ├─── SocketManagement/ # Client-server connection management.
│ └└── View/ # User interaction.
├─── Server/
│ ├─── Controller/ # Main server logic.
│ ├─── Model/ # Server data models.
│ ├─── Service/ # Services for word processing.
│ ├─── SocketManagement/ # Server-client connection management.
│ ├─── Task/ # Processing tasks.
│ └└─── ThreadManagement/ # Thread pool management.
├─── resources/ # Configuration files and other resources.
└─── README.md # Project documentation.
```

## Execution Example
1. Running server:
```
Server is running on port 8080
Client connected.
Received paths: /path/to/original.txt and /path/to/copy.txt
Result sent to client: 1

```

2. Interactive client:
```
Press enter to select the original file:
> [File Selector Opens]
Press enter to select the text to compare:
> [File Selector Opens]
The similarity percentage is: 85.76%
The words that were misspelled are: [["teh", "the"], ["compair", "compare"]]

```

## PRD

**File Comparison System Using the Longest Common Subsequence (LCS) Algorithm**

### Product Definition
This system is designed to allow users to load and compare pairs of text files by generating a 
similarity analysis based on the Longest Common Subsequence (LCS) algorithm. The function of the algorithm is to identify 
the longest common subsequence between the two documents, providing a similarity metric useful for various applications, 
such as plagiarism detection, text comparison or similar code analysis.
### Objective
The purpose of this system is to provide a simple but efficient tool to analyze similarities between text files by processing 
requests asynchronously. This allows users to upload files without blocking the interface, check the status of the process, 
and obtain results quickly. The system design aims to allow multiple users to perform analysis simultaneously.

### Functional Requirements
- Allow users to upload two text files or documents.
- Process these files using the LCS algorithm to find the longest common subsequence.
- Provide a result indicating the percentage of similarity along with the common subsequence.
- Monitor and display the status of each processing task (pending, in process, completed, failed).
- Make it easy for users to query the final response of their task.
### Non-functional Requirements
Scalability: Support multiple requests at the same time.
Performance: Process files of moderate size in the shortest possible time.
Reliability: Implement automatic retries in case of temporary errors.

### Scope
This project is limited to implementing a system that compares text files using the LCS algorithm and provides basic state handling for asynchronous processing. The results will be presented in a simple way, showing the common subsequence and the percentage of similarity. It is limited to .txt files and does not include processing of other file types or integration of other comparison algorithms.
### Risks
- Insufficient Performance under High Load: Processing may slow down if large files are loaded or if multiple users are active at the same time. Proper sizing of processing resources will be key.
- Invalid File Errors: Uploading files in unsupported formats could cause failures. Validations should be implemented to ensure that only text files are processed.
- Dependency on the LCS Algorithm: The LCS algorithm can be resource intensive, especially with long texts, which could affect efficiency.
- Asynchronous Handling Failures: Improper management of processing queues could cause excessive retries or leave incomplete tasks.