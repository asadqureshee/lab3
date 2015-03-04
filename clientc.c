#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h>

int main(void)
{
    struct sockaddr_in serv_addr;
    int sockfd = 0;
    int bytesReceived = 0;
    char recvBuff[256];
    //memset(recvBuff, '0', sizeof(recvBuff));
    


    if((sockfd = socket(AF_INET, SOCK_STREAM, 0))< 0)
    {
        printf("\n Error : Could not create socket \n");
        return 1;
    }


    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(55556); // port
    serv_addr.sin_addr.s_addr = inet_addr("172.16.69.1");




    if(connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr))<0)
    {
        printf("\n Error : Connect Failed \n");
        return 1;
    }

    FILE *fp;
    fp = fopen("transferFile.txt", "w"); 
    if(NULL == fp)
    {
        printf("Error opening file");
        return 1;
    }

   
  
    while((bytesReceived = read(sockfd, recvBuff, 256)) > 0)
    {
        printf("Bytes received %d\n",bytesReceived);    
        // recvBuff[n] = 0;
        fwrite(recvBuff, 1,bytesReceived,fp);
	char recvBuff1[bytesReceived];
	
	//char recvBuff*
         printf("%s \n", recvBuff);
    }

   

    if(bytesReceived < 0)
    {
        printf("\n Read Error \n");
    }


    return 0;
}
